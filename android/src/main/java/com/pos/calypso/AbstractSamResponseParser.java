package com.pos.calypso;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSamResponseParser extends AbstractApduResponseParser {

  protected static final Map<Integer, StatusProperties> STATUS_TABLE;

  static {
    Map<Integer, StatusProperties> m =
        new HashMap<Integer, StatusProperties>(AbstractApduResponseParser.STATUS_TABLE);
    m.put(
        0x6D00,
        new StatusProperties("Instruction unknown.", CalypsoSamIllegalParameterException.class));
    m.put(
        0x6E00,
        new StatusProperties("Class not supported.", CalypsoSamIllegalParameterException.class));
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
   * Constructor to build a parser of the APDU response.
   *
   * @param response response to parse.
   * @param builder the reference of the builder that created the parser.
   */
  protected AbstractSamResponseParser(
      ApduResponseApi response,
      AbstractSamCommandBuilder<? extends AbstractSamResponseParser> builder) {
    super(response, builder);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public final AbstractSamCommandBuilder<AbstractSamResponseParser> getBuilder() {
    return (AbstractSamCommandBuilder<AbstractSamResponseParser>) super.getBuilder();
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  protected final CalypsoApduCommandException buildCommandException(
      Class<? extends CalypsoApduCommandException> exceptionClass,
      String message,
      CardCommand commandRef,
      Integer statusWord) {

    CalypsoApduCommandException e;
    CalypsoSamCommand command = (CalypsoSamCommand) commandRef;
    if (exceptionClass == CalypsoSamAccessForbiddenException.class) {
      e = new CalypsoSamAccessForbiddenException(message, command, statusWord);
    } else if (exceptionClass == CalypsoSamCounterOverflowException.class) {
      e = new CalypsoSamCounterOverflowException(message, command, statusWord);
    } else if (exceptionClass == CalypsoSamDataAccessException.class) {
      e = new CalypsoSamDataAccessException(message, command, statusWord);
    } else if (exceptionClass == CalypsoSamIllegalArgumentException.class) {
      e = new CalypsoSamIllegalArgumentException(message, command);
    } else if (exceptionClass == CalypsoSamIllegalParameterException.class) {
      e = new CalypsoSamIllegalParameterException(message, command, statusWord);
    } else if (exceptionClass == CalypsoSamIncorrectInputDataException.class) {
      e = new CalypsoSamIncorrectInputDataException(message, command, statusWord);
    } else if (exceptionClass == CalypsoSamSecurityDataException.class) {
      e = new CalypsoSamSecurityDataException(message, command, statusWord);
    } else {
      e = new CalypsoSamUnknownStatusException(message, command, statusWord);
    }
    return e;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public void checkStatus() throws CalypsoSamCommandException {
    try {
      super.checkStatus();
    } catch (CalypsoApduCommandException e) {
      throw (CalypsoSamCommandException) e;
    }
  }
}
