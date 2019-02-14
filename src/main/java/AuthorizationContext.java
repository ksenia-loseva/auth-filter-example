class AuthorizationContext {
    private static ThreadLocal<String> authToken = ThreadLocal.withInitial(() -> null);

    static void setAuthToken(String token) {
        authToken.set(token);
    }

    static String getToken() {
        return authToken.get();
    }
}
