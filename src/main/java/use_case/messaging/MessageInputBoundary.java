package use_case.messaging;

import models.MessageModel;

public interface MessageInputBoundary {
    void handleSendMessage(MessageModel msgModel);

    void handleRetrieveMessages(MessageModel msgModel);
}
