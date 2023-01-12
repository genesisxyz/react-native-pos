import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-pos' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const ByteUtils = NativeModules.ByteUtils
  ? NativeModules.ByteUtils
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export async function stringToByteArray(
  str: string
): Promise<Uint8Array | null> {
  const bytes = await ByteUtils.stringToByteArray(str);
  return bytes ? new Uint8Array(bytes) : null;
}

export async function bytesFromString(str: string): Promise<Uint8Array | null> {
  const bytes = await ByteUtils.bytesFromString(str);
  return bytes ? new Uint8Array(bytes) : null;
}

export async function bytesToHexString(
  bytes: Uint8Array
): Promise<string | null> {
  return await ByteUtils.bytesToHexString(Array.from(bytes));
}

export async function shiftRight(
  bytes: Uint8Array,
  n: number
): Promise<Uint8Array> {
  return new Uint8Array(await ByteUtils.shiftRight(Array.from(bytes), n));
}

export async function stringFromByteArray(bytes: Uint8Array): Promise<string> {
  return await ByteUtils.stringFromByteArray(bytes);
}
