package org.treasure.accountservice.event.dto;

import java.time.Instant;
import java.util.UUID;

public record AccountCreatedEvent(UUID eventId,
                                  UUID accountId,
                                  String status,
                                  Instant occurredAt,
                                  Integer eventVersion) {
}
