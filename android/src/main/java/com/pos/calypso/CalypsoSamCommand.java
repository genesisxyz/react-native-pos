package com.pos.calypso;

enum CalypsoSamCommand implements CardCommand {

  /** select diversifier. */
  SELECT_DIVERSIFIER("Select Diversifier", (byte) 0x14),

  /** select diversifier. */
  COMPUTE_SIGNATURE("Compute Signature", (byte) 0x2A),

  /** get challenge. */
  GET_CHALLENGE("Get Challenge", (byte) 0x84),

  /** digest init. */
  DIGEST_INIT("Digest Init", (byte) 0x8A),

  /** digest update. */
  DIGEST_UPDATE("Digest Update", (byte) 0x8C),

  /** digest update multiple. */
  DIGEST_UPDATE_MULTIPLE("Digest Update Multiple", (byte) 0x8C),

  /** digest close. */
  DIGEST_CLOSE("Digest Close", (byte) 0x8E),

  /** digest authenticate. */
  DIGEST_AUTHENTICATE("Digest Authenticate", (byte) 0x82),

  /** digest authenticate. */
  GIVE_RANDOM("Give Random", (byte) 0x86),

  /** digest authenticate. */
  CARD_GENERATE_KEY("Card Generate Key", (byte) 0x12),

  /** card cipher PIN. */
  CARD_CIPHER_PIN("Card Cipher PIN", (byte) 0x12),

  /** unlock. */
  UNLOCK("Unlock", (byte) 0x20),

  /** write key. */
  WRITE_KEY("Write Key", (byte) 0x1A),

  /** read key parameters. */
  READ_KEY_PARAMETERS("Read Key Parameters", (byte) 0xBC),

  /** read event counter. */
  READ_EVENT_COUNTER("Read Event Counter", (byte) 0xBE),

  /** read ceilings. */
  READ_CEILINGS("Read Ceilings", (byte) 0xBE),

  /** SV check. */
  SV_CHECK("SV Check", (byte) 0x58),

  /** SV prepare debit. */
  SV_PREPARE_DEBIT("SV Prepare Debit", (byte) 0x54),

  /** SV prepare load. */
  SV_PREPARE_LOAD("SV Prepare Load", (byte) 0x56),

  /** SV prepare undebit. */
  SV_PREPARE_UNDEBIT("SV Prepare Undebit", (byte) 0x5C);

  /** The name. */
  private final String name;

  /** The instruction byte. */
  private final byte instructionByte;

  /**
   * The generic constructor of CalypsoCommands.
   *
   * @param name the name.
   * @param instructionByte the instruction byte.
   * @since 2.0.0
   */
  CalypsoSamCommand(String name, byte instructionByte) {
    this.name = name;
    this.instructionByte = instructionByte;
  }

  /**
   * Gets the name.
   *
   * @return A String
   * @since 2.0.0
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the instruction byte (INS).
   *
   * @return A byte
   * @since 2.0.0
   */
  public byte getInstructionByte() {
    return instructionByte;
  }
}
