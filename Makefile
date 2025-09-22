PUBLISHER_NAME=vpr43
BACKEND_IMAGE_NAME=bogdan-backend
VERSION=1.0

build:
	@echo Start building...
	@docker build -t $(PUBLISHER_NAME)/$(BACKEND_IMAGE_NAME):$(VERSION) .

up:
	@echo Starting containers...
	docker compose up -d

down:
	@docker compose down

reup: down up
