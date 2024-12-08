package me.julionxn;

import me.julionxn.instance.MinecraftInstance;
import me.julionxn.instance.MinecraftOptions;
import me.julionxn.instance.PlayerInfo;
import me.julionxn.profile.Profile;
import me.julionxn.profile.URLProfile;
import me.julionxn.profile.URLProfiles;
import me.julionxn.version.MinecraftVersion;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        LauncherData launcherData = new LauncherData("JNLauncher", "1.0", "C:/");
        Launcher launcher = new Launcher(launcherData);
        launcher.start();
        URLProfiles profiles = launcher.getProfilesController().getProfilesFrom("http://localhost/greeko/");
        URLProfile urlProfile = profiles.getAllProfiles().get(0);
        Optional<Profile> profileOpt = urlProfile.tempProfile().save();
        if (profileOpt.isEmpty()) return;
        Profile profile = profileOpt.get();
        MinecraftOptions minecraftOptions = new MinecraftOptions();
        MinecraftVersion version = urlProfile.minecraftVersion();
        launcher.getVersionsController().installVersion(version, (status, progress) -> {

        });
        MinecraftInstance instance = new MinecraftInstance(launcher, version, minecraftOptions, profile, new PlayerInfo("pepe", "xd", "a"));
        instance.run();


        /* ==================== WITHOUT URL FETCHING ======================
        Optional<MinecraftVersion> versionOpt = launcher.getVersionsController()
                .installVersion("1.21.3", new FabricLoader("0.16.9"), (status, progress) -> {
                    //empty
                });
        if (versionOpt.isEmpty()){
            System.err.println("Error installing version.");
            return;
        }
        MinecraftVersion version = versionOpt.get();
        Optional<Profile> profileOpt = launcher.getProfilesController().getProfile("testing");
        if (profileOpt.isEmpty()){
            System.err.println("Error installing profile.");
            return;
        }
        Profile profile = profileOpt.get();
        MinecraftOptions minecraftOptions = new MinecraftOptions();
        MinecraftInstance instance = new MinecraftInstance(launcher,
                version, minecraftOptions, profile,
                new PlayerInfo("pepe", "xd", "a"));
        instance.run();

         */
    }
}