# TeamCity Kubernetes Support Plugin
[![official JetBrains project](http://jb.gg/badges/official.svg)](https://plugins.jetbrains.com/plugin/14315-helm-support)
[![plugin status](https://teamcity.jetbrains.com/app/rest/builds/buildType:(id:TeamCityPluginsByJetBrains_TeamCityHelmPlugin_BuildMaster)/statusIcon.svg)](https://teamcity.jetbrains.com/viewType.html?buildTypeId=TeamCityPluginsByJetBrains_TeamCityHelmPlugin_BuildMaster&guest=1)

Support [Helm](https://docs.helm.sh/) build steps.

## Compatibility

The plugin is compatible with TeamCity 2017.1.x and later.

## Installation

You can [download the plugin](https://teamcity.jetbrains.com/guestAuth/app/rest/builds/buildType:TeamCityPluginsByJetBrains_TeamCityHelmPlugin_BuildMaster,pinned:true/artifacts/content/teamcity-helm-plugin.zip) and install it as an [additional TeamCity plugin](https://www.jetbrains.com/help/teamcity/?Installing+Additional+Plugins).

## Configuration

Add **Helm** build step to build configuration, choose one of supported commands: [install](https://docs.helm.sh/helm/#helm-install), [upgrade](https://docs.helm.sh/helm/#helm-upgrade), [rollback](https://docs.helm.sh/helm/#helm-rollback), [test](https://docs.helm.sh/helm/#helm-test), [delete](https://docs.helm.sh/helm/#helm-delete). 

Or use Kotlin DSL

```kotlin
object Helm_Deployment : BuildType({
    uuid = "866dd903-6f55-4a54-a621-065b380dd7fc"
    extId = "Helm_Deployment"
    name = "Deployment"

    steps {
        helmInstall {
            chart = "stable/teamcity-server"
            param("teamcity.helm.command", "helm-install")
        }
    }
})
```

Build agent to be compatible with Helm runner should provide **Helm_Path** configuration parameter which should point to the location of Helm executable. 
Plugin searches Helm in default location **/usr/local/bin/helm** on Linux machines.

## License

Apache 2.0

## Feedback

Please feel free to post feedback in the repository [issues](https://youtrack.jetbrains.com/issues/TW).

## Contributing guidelines

Follow general instructions to [build TeamCity plugins using Maven](https://plugins.jetbrains.com/docs/teamcity/developing-plugins-using-maven.html).
Plugin uses [TeamCity SDK Maven plugin](https://github.com/JetBrains/teamcity-sdk-maven-plugin)

``` bash
# build and package plugin
mvn clean package

# deploy packed plugin to local Teamcity installation and start server and build agent
mvn tc-sdk:start

# stop locally running server and build agent
mvn tc-sdk:stop
```
