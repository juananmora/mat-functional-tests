#!/bin/bash

#export GITHUB_USER=
#export GITHUB_PASSWORD=

set -e

docker-compose up -d

url="http://localhost:4444/wd/hub/status"
wait_interval_in_seconds=1
max_wait_time_in_seconds=300
end_time=$((SECONDS + max_wait_time_in_seconds))
time_left=$max_wait_time_in_seconds
timeout_reached=true  # Flag para saber si el timeout se ha alcanzado

while [ $SECONDS -lt $end_time ]; do
    response=$(curl -sL "$url" | jq -r '.value.ready')
    if [ -n "$response" ] && [ "$response" = "true" ]; then
        echo "Selenium Grid is up - executing tests"
        mvn clean test --settings maven/settings.xml -Dselenium_url="http://localhost:4444/wd/hub" -Dheadless=false
        timeout_reached=false  # La Grid está lista, no se alcanzó el timeout
        break
    else
        echo "Waiting for the Grid. Sleeping for $wait_interval_in_seconds second(s). $time_left seconds left until timeout."
        sleep $wait_interval_in_seconds
        time_left=$((time_left - wait_interval_in_seconds))
    fi
done

# Apagar los contenedores de Selenium siempresss
docker-compose down

# Comprobar si se alcanzó el timeout
if [ "$timeout_reached" = true ]; then
    echo "Timeout: The Grid was not started within $max_wait_time_in_seconds seconds."
    exit 1
fi

