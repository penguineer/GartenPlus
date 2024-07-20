# GartenPlus

> Specific Garten Management for our group


## Configuration

Configuration is done using environment variables:

* `PORT`: Port for the HTTP endpoint (default `8080`, only change when running locally!)
* `ASSIGN_ADMINISTRATOR_IF_MISSING`: Make everyone an Administrator if none is found in the database (default `false`)
* `OAUTH_CALLBACK_BASE_URI`: Base URI for the OAuth callback (defaults to `http://localhost:8080`)
* `GITHUB_OAUTH_CLIENT_ID`: GitHub OAuth Client ID (defaults to none and will disable GitHub authentication if not set)
* `GITHUB_OAUTH_CLIENT_SECRET`: GitHub OAuth Client Secret (defaults to none and will disable GitHub authentication if not set)
* `MYSQL_HOST`: MySQL host (defaults to `localhost`)
* `MYSQL_PORT`: MySQL port (defaults to `3306`)
* `MYSQL_DB`: MySQL database (defaults to `gartenplus`)
* `MYSQL_USER`: MySQL user (defaults to `gartenplus`)
* `MYSQL_PASS`: MySQL password


## Maintainers

* Stefan Haun ([@penguineer](https://github.com/penguineer))


## Contributing

PRs are welcome!

If possible, please stick to the following guidelines:

* Keep PRs reasonably small and their scope limited to a feature or module within the code.
* If a large change is planned, it is best to open a feature request issue first, then link subsequent PRs to this issue, so that the PRs move the code towards the intended feature.


## License

[MIT](LICENSE.txt) © 2024 Stefan Haun and contributors