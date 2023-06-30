# Function

Welcome to your new Function project!

This sample project contains a single function based on Spring Cloud Function: `functions.FunctionApplication.echo()`, which returns an echo of the data passed via HTTP request.

## Pre-requisites

* Java 17+
* Podman or Docker

## Local execution

Run the application locally on port 8080.

```shell script
./gradlew bootRun
```

You can also run the auto tests.

```shell script
./gradlew test
```

## The `func` CLI

It's recommended to set `FUNC_REGISTRY` environment variable.

```shell script
# replace ~/.bashrc by your shell rc file
# replace docker.io/johndoe with your registry
export FUNC_REGISTRY=docker.io/johndoe
echo "export FUNC_REGISTRY=docker.io/johndoe" >> ~/.bashrc
```

### Building

This command builds an OCI image for the function. By default, this will build a JVM image.

```shell script
func build -v                  # build image
```

**Note**: If you want to disable the native build, you need to edit the `func.yaml` file and
set the `BP_NATIVE_IMAGE` BuilderEnv variable to false:

```yaml
buildEnvs:
  - name: BP_NATIVE_IMAGE
    value: "false"
```

### Running

This command runs the func locally in a container using the image created above.

```shell script
func run
```

### Deploying

This command will build and deploy the function into cluster.

```shell script
func deploy -v # also triggers build
```

### For ARM processor based systems

Building Spring Boot apps with Paketo Buildpacks on an ARM processor based system, like an Apple Macbook with an M1 or M2 chip, is not supported officially. Use the community suppported builder: `dashaun/builder-arm:tiny`.

## Function invocation

For the examples below, please be sure to set the `URL` variable to the route of your function.

You get the route by following command.

```shell script
func info
```

Note the value of **Routes:** from the output, set `$URL` to its value.

__TIP__:

If you use `kn` then you can set the url by:

```shell script
# kn service describe <function name> and show route url
export URL=$(kn service describe $(basename $PWD) -ourl)
```

### func

Using `func invoke` command with Path-Based routing:

```shell script
func invoke --target "$URL/echo" --data "$(whoami)"
```

If your function class only contains one function, then you can leave out the target path:

```shell script
func invoke --data "$(whoami)"
```

### cURL

```shell script
curl -v "$URL/echo" \
  -H "Content-Type:text/plain" \
  -w "\n" \
  -d "$(whoami)"
```

If your function class only contains one function, then you can leave out the target path:

```shell script
curl -v "$URL" \
  -H "Content-Type:text/plain" \
  -w "\n" \
  -d "$(whoami)"
```

### HTTPie

```shell script
echo "$(whoami)" | http -v "$URL/echo"
```

If your function class only contains one function, then you can leave out the target path:

```shell script
echo "$(whoami)" | http -v "$URL"
```

## Cleanup

To clean the deployed function run:

```shell
func delete
```
