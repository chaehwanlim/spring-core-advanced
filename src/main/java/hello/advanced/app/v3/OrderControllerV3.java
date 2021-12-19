package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace tracer;

    @GetMapping("/v0/request")
    public String request(String itemId) {
        TraceStatus status = null;

        try {
            status = tracer.begin("OrderController.request()");

            orderService.orderItem(itemId);

            tracer.end(status);

            return "ok";
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }

}
