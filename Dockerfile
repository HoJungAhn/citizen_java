FROM codercom/enterprise-java:ubuntu
COPY [ "configure", "/coder/configure" ]
ADD ./vsix/vscjava.vscode-java-debug-0.41.2022061304.vsix /vsix
ADD ./vsix/vscjava.vscode-java-pack-0.23.2022061300.vsix /vsix
USER coder
