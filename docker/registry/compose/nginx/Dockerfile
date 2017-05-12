FROM nginx:1.7

COPY nginx.conf /etc/nginx/nginx.conf
COPY registry.conf /etc/nginx/conf.d/registry.conf
COPY docker-registry-v2.conf /etc/nginx/docker-registry-v2.conf

COPY ca/domain.pub /etc/nginx/domain.crt
COPY ca/domain.key /etc/nginx/domain.key
COPY ca/ca-root.pem /etc/nginx/ca.crt

EXPOSE 8080