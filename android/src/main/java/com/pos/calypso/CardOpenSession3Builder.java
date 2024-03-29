package com.pos.calypso;

import org.eclipse.keyple.core.util.ApduUtil;

public final class CardOpenSession3Builder
    extends AbstractCardOpenSessionBuilder<AbstractCardOpenSessionParser> {

  private static final CalypsoCardCommand command = CalypsoCardCommand.OPEN_SESSION_3X;

  // Construction arguments used for parsing
  private final int sfi;
  private final int recordNumber;

  /**
   * Instantiates a new AbstractCardOpenSessionBuilder.
   *
   * @param keyIndex the key index.
   * @param samChallenge the sam challenge returned by the SAM Get Challenge APDU command.
   * @param sfi the sfi to select.
   * @param recordNumber the record number to read.
   * @throws IllegalArgumentException - if the request is inconsistent
   * @since 2.0.0
   */
  public CardOpenSession3Builder(byte keyIndex, byte[] samChallenge, int sfi, int recordNumber) {
    super(command);

    this.sfi = sfi;
    this.recordNumber = recordNumber;

    byte p1 = (byte) ((recordNumber * 8) + keyIndex);
    byte p2;
    byte[] dataIn;

//    if (!calypsoCard.isExtendedModeSupported()) {
    if (true) {
      p2 = (byte) ((sfi * 8) + 1);
      dataIn = samChallenge;
    } else {
      p2 = (byte) ((sfi * 8) + 2);
      dataIn = new byte[samChallenge.length + 1];
      dataIn[0] = (byte) 0x00;
      System.arraycopy(samChallenge, 0, dataIn, 1, samChallenge.length);
    }

    /*
     * case 4: this command contains incoming and outgoing data. We define le = 0, the actual
     * length will be processed by the lower layers.
     */
    byte le = 0;

    setApduRequest(
        new ApduRequestAdapter(
            ApduUtil.build(
                (byte)0x00,
                command.getInstructionByte(),
                p1,
                p2,
                dataIn,
                null)));
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public CardOpenSession3Parser createResponseParser(ApduResponseApi apduResponse) {
    return new CardOpenSession3Parser(apduResponse, this);
  }

  /**
   * {@inheritDoc}
   *
   * <p>This command can't be executed in session and therefore doesn't uses the session buffer.
   *
   * @return false
   * @since 2.0.0
   */
  @Override
  public boolean isSessionBufferUsed() {
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public int getSfi() {
    return sfi;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public int getRecordNumber() {
    return recordNumber;
  }
}
