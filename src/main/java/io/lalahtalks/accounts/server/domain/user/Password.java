package io.lalahtalks.accounts.server.domain.user;

import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "value")
public class Password {

    @NonNull String value;

}
