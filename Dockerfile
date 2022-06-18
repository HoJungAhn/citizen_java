FROM codercom/enterprise-java:ubuntu
COPY [ "configure", "/coder/configure" ]
USER coder
