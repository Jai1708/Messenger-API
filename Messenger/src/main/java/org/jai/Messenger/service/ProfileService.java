package org.jai.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.jai.Messenger.database.DatabaseClass;
import org.jai.Messenger.exception.DataNotFoundException;
import org.jai.Messenger.model.Like;
import org.jai.Messenger.model.Message;
import org.jai.Messenger.model.Profile;

public class ProfileService {

	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {

		profiles.put("jai", new Profile(1L, "jai", "Jai", "Purohit"));
		profiles.put("sameer", new Profile(2L, "sameer", "Sameer", "Purohit"));
		profiles.put("rajesh", new Profile(3L, "rajesh", "Rajesh", "Purohit"));

	}

	public List<Profile> getAllProfiles() {

		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {

		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {

		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;

	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {

			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;

	}

	public Profile removeProfile(String profileName) {

		return profiles.remove(profileName);

	}
	
	public List<Profile> getAllProfilesforYear(int year) {
		List<Profile> profilesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Profile profile : profiles.values()) {
			cal.setTime(profile.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				profilesForYear.add(profile);
			}
		}

		return profilesForYear;
	}

	public List<Profile> getAllProfilePaginated(int start, int size) {
		List<Profile> list = new ArrayList<Profile>(profiles.values());
		if (start > list.size() || size > list.size()) {
			return new ArrayList<Profile>();
		}
		return list.subList(start, size);
	}


}
