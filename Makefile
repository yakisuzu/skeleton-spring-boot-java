.PHONY: help

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

app-debug: ## sh
	@docker exec -it $(shell docker ps -f "name=app-local_app" --format "{{.ID}}") /bin/sh

session-show: ## redis-cli keys *
	@docker exec -t $(shell docker ps -f "name=app-local_session" --format "{{.ID}}") redis-cli keys \*

docker-compose-up: ## docker-compose up
	@docker-compose -f ops/docker-compose/base/docker-compose.yml -p app-local up --build

docker-compose-down: ## docker-compose down
	@docker-compose -f ops/docker-compose/base/docker-compose.yml -p app-local down

docker-rmi-none: ## docker rmi <none> images
	@docker rmi $(shell docker images -f "dangling=true" -q)
	@docker images
