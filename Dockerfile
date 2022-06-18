FROM codercom/enterprise-java:ubuntu
COPY [ "configure", "/coder/configure" ]
COPY ./vsix/vscjava.vscode-java-debug-0.41.2022061304.vsix /coder
COPY ./vsix/vscjava.vscode-java-pack-0.23.2022061300.vsix /coder
USER coder
