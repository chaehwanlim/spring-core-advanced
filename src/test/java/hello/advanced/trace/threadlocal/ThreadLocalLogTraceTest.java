package hello.advanced.trace.threadlocal;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_end_level2_with_exception() {
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}