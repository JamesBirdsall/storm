package org.apache.storm.eventhubs.spout;

import org.apache.storm.eventhubs.core.EventHubConfig;

public class EventHubSpoutConfig extends EventHubConfig {
    private static final long serialVersionUID = 1L;
    private String targetAddress;
    private String outputStreamId;
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount) {
        super(namespace, entityPath, username, password, partitionCount);
    }
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount, String zkConnectionString) {
        this(username, password, namespace, entityPath, partitionCount);
        setZkConnectionString(zkConnectionString);
    }
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount, String zkConnectionString, int checkpointIntervalInSeconds, int receiverCredits) {
        this(username, password, namespace, entityPath, partitionCount, zkConnectionString);
        setCheckpointIntervalInSeconds(checkpointIntervalInSeconds);
        setReceiverCredits(receiverCredits);
    }
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount, String zkConnectionString, int checkpointIntervalInSeconds, int receiverCredits,
    		long enqueueTimeFilter) {
        this(username, password, namespace, entityPath, partitionCount, zkConnectionString,
        		checkpointIntervalInSeconds, receiverCredits);
        setEnqueueTimeFilter(enqueueTimeFilter);
    }
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount, String zkConnectionString, int checkpointIntervalInSeconds, int receiverCredits,
    		int maxPendingMsgsPerPartition, long enqueueTimeFilter) {
        this(username, password, namespace, entityPath, partitionCount, zkConnectionString,
        		checkpointIntervalInSeconds, receiverCredits);
        setMaxPendingMsgsPerPartition(maxPendingMsgsPerPartition);
        setEnqueueTimeFilter(enqueueTimeFilter);
    }
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount, String zkConnectionString, int checkpointIntervalInSeconds, int receiverCredits,
    		int maxPendingMsgsPerPartition, long enqueueTimeFilter, int batchSize) {
        this(username, password, namespace, entityPath, partitionCount, zkConnectionString,
        		checkpointIntervalInSeconds, receiverCredits);
        setMaxPendingMsgsPerPartition(maxPendingMsgsPerPartition);
        setEnqueueTimeFilter(enqueueTimeFilter);
    }
    
    public EventHubSpoutConfig(String username, String password, String namespace, String entityPath,
    		int partitionCount, int receiveEventsMaxCount) {
        this(username, password, namespace, entityPath, partitionCount);
        setReceiveEventsMaxCount(receiveEventsMaxCount);
    }
    
    public String getOutputStreamId() {
        return this.outputStreamId;
    }
    
    public void setOutputStreamId(String outputStreamId) {
        this.outputStreamId = outputStreamId;
    }
    
    public String getTargetAddress() {
        return this.targetAddress;
    }
    
    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }
}
