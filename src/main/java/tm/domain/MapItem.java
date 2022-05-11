package tm.domain;

import tm.domain.Mountain;
import tm.domain.Treasury;

public sealed interface MapItem permits Treasury, Mountain {
}
