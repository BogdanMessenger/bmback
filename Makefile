ifneq (,$(wildcard ./.env))
    include .env
    export
endif

build:
	@docker build -t $(BACKEND_PUBLISHER_NAME)/$(BACKEND_IMAGE_NAME):$(BACKEND_VERSION) .

up:
	docker compose up -d

down:
	@docker compose down

reup: down up
