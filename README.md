# RaiderIO mobile app

## Build instruction
Go to https://develop.battle.net/access/clients and create a new client.

Register a new redirected URL in your client and change `redirected_url` in `app/gradle.properties`.
Update `HOSTNAME`, `SCHEME` and `PREFIX` with the URL.

Notice that `PREFIX` is not required if your URL doesn't have one

Add your Client ID and Client Secret as PATH variable with :

- `BLIZZARD_CLIENT_ID`
- `BLIZZARD_CLIENT_SECRET`

Then build
