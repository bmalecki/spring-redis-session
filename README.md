```sh

IMAGE=spring-redis
TAG=latest
ACR_NAME=someacrname
./gradlew bootBuildImage --imageName=$ACR_NAME.azurecr.io/${IMAGE}:${TAG}
docker push $ACR_NAME.azurecr.io/${IMAGE}:${TAG}
kubectl scale --replicas=0 deployment/spring-redis
kubectl apply -f deployment.yml


export REDIS_URL=
export REDIS_PASS=
export AZURE_URL=AAAAA.westeurope.aksapp.io
envsubst < deployment.yml | kubectl apply -f -
```


```sh
docker run --name redis -p6379:6379 --rm redis:6.0.9
export REDIS_URL=localhost
export REDIS_PASS=
./gradlew bootRun
```

https://spring.io/projects/spring-session-data-redis

https://spring.io/projects/spring-session-data-redis#samples

https://docs.spring.io/spring-session/docs/current/reference/html5/guides/java-redis.htmlg
