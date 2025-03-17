package javagnomes.lab4.security;

public record HashPlusSalt(byte[] hash, byte[] salt) {
}
