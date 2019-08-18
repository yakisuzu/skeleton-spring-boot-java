.PHONY: help

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

show-session: ## redis-cli keys *
	@docker exec -t $(shell docker ps -f "name=session" --format "{{.ID}}") redis-cli keys \*

run-docker-compose: ## docker-compose up -d
	@docker-compose -f ops/docker-compose/base/docker-compose.yml -p app-local up
