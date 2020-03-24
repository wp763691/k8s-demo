FROM java:8
VOLUME /tmp

RUN export LANG="zh_CN.UTF-8"
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone

EXPOSE 8001

ADD *.jar /app.jar
ENTRYPOINT ["sh", "-c", "$APP_RUN"]