# For building the package:

mvn clean package

# For building docker image:

copy jar from target directory into docker folder

docker build -t com.videostreamingapi:video-streaming-api-v{version_number} .

# For running docker image:

docker run -p 8080:8080 --env-file {env_file_location} com.videostreamingapi:v
ideo-streaming-api-v{version_number}

# Local example docker compose orchestration:

docker compose up -d
