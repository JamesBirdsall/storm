package org.apache.storm.eventhubs.core;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventData.SystemProperties;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;
import org.apache.storm.eventhubs.format.SerializeDeserializeUtil;

public class EventHubMessage implements Comparable<EventHubMessage> {
    private final byte[] content;
    private final String partitionId;
    private final MessageId messageId;
    private final Map<String, Object> applicationProperties;
    private final SystemProperties systemProperties;
      
    public EventHubMessage(EventData eventdata, String partitionId) {
        this.partitionId = partitionId;
        if (eventdata.getBytes() != null) {
            this.content = eventdata.getBytes();
        } else if (eventdata.getObject() != null) {
            try
            {
                this.content = SerializeDeserializeUtil.serialize(eventdata.getObject());
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Failed to retrieve payload from EventData");
        }
        this.applicationProperties = eventdata.getProperties();
        this.systemProperties = eventdata.getSystemProperties();
        this.messageId = new MessageId(partitionId, getOffset(), getSequenceNumber());
    }
      
    public String readAsString() {
        return readAsString(Charset.defaultCharset());
    }
      
    public String readAsAsciiString() {
        return readAsString(StandardCharsets.US_ASCII);
    }
      
    public String readAsUtf8String() {
        return readAsString(StandardCharsets.UTF_8);
    }
      
    public String readAsUtf16String() {
        return readAsString(StandardCharsets.UTF_16);
    }
      
    public String readAsString(Charset charset) {
        return new String(this.content, charset);
    }
      
    public byte[] getContent() {
        return this.content;
    }
      
    public String getPartitionId() {
        return this.partitionId;
    }
      
    public String getPartitionKey() {
        return (this.systemProperties != null) ? this.systemProperties.getPartitionKey() : null;
    }
      
    public String getOffset() {
        return (this.systemProperties != null) ? this.systemProperties.getOffset() : null;
    }
      
    public Instant getEnqueuedTime() {
        return (this.systemProperties != null) ? this.systemProperties.getEnqueuedTime() : null;
    }
      
    public Long getSequenceNumber() {
        return (this.systemProperties != null) ? this.systemProperties.getSequenceNumber() : 0L;
    }
      
    public String getPublisher() {
        return (this.systemProperties != null) ? this.systemProperties.getPublisher() : null;
    }
      
    public Map<String, Object> getApplicationProperties() {
        return this.applicationProperties;
    }
      
    public Map<String, Object> getSystemProperties() {
        return this.systemProperties;
    }
      
    public MessageId getMessageId() {
        return this.messageId;
    }
      
    public int compareTo(EventHubMessage o) {
        return getSequenceNumber().compareTo(o.getSequenceNumber());
    }  
}
