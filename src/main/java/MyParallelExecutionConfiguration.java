import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;

public class MyParallelExecutionConfiguration implements ParallelExecutionConfiguration {
    private final int parallelism;
    private final int minimumRunnable;
    private final int maxPoolSize;
    private final int corePoolSize;
    private final int keepAliveSeconds;

    MyParallelExecutionConfiguration(int parallelism, int minimumRunnable, int maxPoolSize, int corePoolSize, int keepAliveSeconds) {
        this.parallelism = parallelism;
        this.minimumRunnable = minimumRunnable;
        this.maxPoolSize = maxPoolSize;
        this.corePoolSize = corePoolSize;
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public int getParallelism() {
        return this.parallelism;
    }

    public int getMinimumRunnable() {
        return this.minimumRunnable;
    }

    public int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public int getKeepAliveSeconds() {
        return this.keepAliveSeconds;
    }
}
