package com.pos.calypso;

import java.util.HashMap;
import java.util.Map;

public final class SamDigestInitParser extends AbstractSamResponseParser {

  private static final Map<Integer, StatusProperties> STATUS_TABLE;

  static {
    Map<Integer, StatusProperties> m =
        new HashMap<Integer, StatusProperties>(AbstractSamResponseParser.STATUS_TABLE);
    m.put(0x6700, new StatusProperties("Incorrect Lc.", CalypsoSamIllegalParameterException.class));
    m.put(
        0x6900,
        new StatusProperties(
            "An event counter cannot be incremented.", CalypsoSamCounterOverflowException.class));
    m.put(
        0x6985,
        new StatusProperties(
            "Preconditions not satisfied.", CalypsoSamAccessForbiddenException.class));
    m.put(0x6A00, new StatusProperties("Incorrect P2.", CalypsoSamIllegalParameterException.class));
    m.put(
        0x6A83,
        new StatusProperties(
            "Record not found: signing key not found.", CalypsoSamDataAccessException.class));
    STATUS_TABLE = m;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  protected Map<Integer, StatusProperties> getStatusTable() {
    return STATUS_TABLE;
  }

  /**
   * Instantiates a new SamDigestInitParser.
   *
   * @param response from SamDigestInitBuilder.
   * @param builder the reference to the builder that created this parser.
   * @since 2.0.0
   */
  public SamDigestInitParser(ApduResponseApi response, SamDigestInitBuilder builder) {
    super(response, builder);
  }
}
