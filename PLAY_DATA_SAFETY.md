# Google Play Data Safety Responses

**App:** Text To Speech (`letshangllc.texttospeech`)  
**Version:** 3.0.0 (versionCode 100)  
**Last Updated:** November 2026  
**Target SDK:** 36

Use this document when filling out the Play Console Data safety form.

---

## Overview

This is a simple on-device text-to-speech utility. User-entered text is spoken locally via the Android `TextToSpeech` engine and is **not** uploaded by the app. The only third-party SDK that collects/shares data is Google AdMob (plus UMP for consent).

---

## Data Collection Summary

| Data Type | Collected | Shared | Purpose |
|-----------|-----------|--------|---------|
| App interactions | Yes | Yes (AdMob) | Advertising / analytics |
| Advertising ID | Yes | Yes (AdMob) | Advertising |
| Device or other IDs | Yes | Yes (AdMob) | Advertising |
| Crash logs | No | No | — |
| Approximate location | Possibly (AdMob) | Yes (AdMob) | Advertising |
| User-entered TTS text | No (local only) | No | App functionality |

---

## Detailed Data Types

### App Activity — App interactions
- **Collected:** Yes (via Google Mobile Ads / AdMob)
- **Shared:** Yes (Google advertising partners)
- **Purpose:** Advertising
- **Optional / consent:** Subject to UMP consent in EEA/UK
- **Encrypted in transit:** Yes (HTTPS)

### Device or other IDs — Advertising ID
- **Collected:** Yes (AdMob)
- **Shared:** Yes
- **Purpose:** Advertising
- **User control:** Device ads settings; UMP consent form in applicable regions
- **Encrypted in transit:** Yes (HTTPS)

### User content — Text entered for speech
- **Collected by developer/backends:** No
- **Processed on device only** by Android TTS
- **Not shared** with AdMob or other SDKs by this app’s code

---

## Third-Party SDKs

### Google Mobile Ads (AdMob)
- **Data:** Advertising ID, app interactions, device info; may infer coarse location for ads
- **Purpose:** Banner ads
- **Privacy:** https://policies.google.com/privacy
- **Traffic:** HTTPS only (`usesCleartextTraffic="false"`; Amazon Ads removed)

### Google User Messaging Platform (UMP)
- **Purpose:** GDPR/consent gathering before ads
- **Data:** Consent preferences
- **Privacy:** https://policies.google.com/privacy

---

## Permissions (Play declarations)

| Permission | Why |
|------------|-----|
| `INTERNET` | Load AdMob creatives / UMP |
| `ACCESS_NETWORK_STATE` | Ads SDK connectivity checks |
| `ACCESS_WIFI_STATE` | Ads SDK connectivity checks |

No location, microphone, storage, contacts, or notification permissions are requested by the app.

---

## Children’s privacy / Families

- App is **not** directed at children under 13 (COPPA) / under 16 (GDPR).
- UMP uses `setTagForUnderAgeOfConsent(false)`.
- If listing in Families, follow Play Families ads policies and AdMob child-directed settings separately.

---

## Play Console form answers (suggested)

1. **Does your app collect or share any of the required user data types?** Yes  
2. **Is all user data collected by your app encrypted in transit?** Yes  
3. **Do you provide a way for users to request that their data be deleted?** Yes — advertising ID reset via device settings; contact developer email on Play listing for other requests. App does not maintain a user account or cloud profile.

---

## Contact

Privacy inquiries: use the developer email on the Google Play Store listing.
