package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 tracer;

    @GetMapping("/v0/request")
    public String request(String itemId) {
        TraceStatus status = null;

        try {
            status = tracer.begin("OrderController.request()");

            orderService.orderItem(status.getTraceId(), itemId);

            tracer.end(status);

            return "ok";
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }

}
