# IdentifySampleApp

In the MainActivity, you need to fill in Api Url, socket url/port, stun url/port, turn url/port/username/password and ident id information.
<pre>
          val identifyObject = IdentifySdk.Builder()
            .api("api url")
            .socket("socket url","socket port")
            .stun("stun url","stun port")
            .turn("turn url","turn port","turn username","turn password")
            .lifeCycle(this.lifecycle)
            .options(identityOptions)
            .build()

        identifyObject.startIdentification(this,"xxxx-xxxx-xxxx-xxxx-xxxxxxx","tr")
</pre>


In settings.gradle, the value value in the credentials section must be filled for your access to the SDK. If you do not have this data, please contact us.


 <pre>allprojects {
repositories {
...
  maven {
            url = 'https://maven.pkg.github.com/2sworks/id24.tr-android-sdk'
            name = "GitHubPackages"
            credentials {
                username = "2sworksservice"
                password = "xxxxxx"
            }
        }
}
}</pre>
