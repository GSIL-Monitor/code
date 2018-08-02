package example.extralib.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Timer其实是 Histogram 和 Meter 的结合， histogram 统计某部分代码/调用的耗时， meter统计代码的TPS
 */
public class TimerExample {
    public static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Timer timer = registry.timer(
                MetricRegistry.name(TimerExample.class,"get-latency"));

        Timer.Context ctx;

        while(true){
            ctx = timer.time();  // 开始计时
            Thread.sleep(random.nextInt(1000));
            ctx.stop();  // 计时结束
        }
    }
}
