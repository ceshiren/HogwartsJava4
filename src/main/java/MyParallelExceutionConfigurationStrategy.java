import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class MyParallelExceutionConfigurationStrategy implements ParallelExecutionConfigurationStrategy {

    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
        //这里可以添加逻辑，动态的调节线程配置
        int parallelism = 4;
        return new MyParallelExecutionConfiguration (parallelism, parallelism, 256 + parallelism, parallelism, 30);
    }
}
