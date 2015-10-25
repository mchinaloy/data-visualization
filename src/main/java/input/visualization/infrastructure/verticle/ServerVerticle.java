package input.visualization.infrastructure.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(ServerVerticle.class);

    @Override
    public void start() throws Exception {
        LOG.info("Starting ServerVerticle");

        Router router = Router.router(vertx);
        BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddressRegex(".*"));
        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {
            if (event.type() == BridgeEvent.Type.SOCKET_CREATED) {
                LOG.info("Socket created...");
            }
            event.complete(true);
        }));
        router.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        LOG.info("ServerVerticle started");
    }

}
