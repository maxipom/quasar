#!/bin/bash

echo "runtime: java17" > app.yaml
echo "env_variables:" >> app.yaml
while IFS='=' read -r key value
do
  if [[ -n "$key" ]]; then
    key=$(echo "$key" | xargs)
    value=$(echo "$value" | xargs)
    echo "  $key: \"$value\"" >> app.yaml
  fi
done < .env.prod

echo "✅ app.yaml generado desde .env.prod"