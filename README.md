# CosmicConvergence

Merge games
- Asteroid
- Agar.io

## Description

An infinite game, where a player controls blobs and avoids colliding with asteroids. Player collects cells scattered around the floor. Each cell increases size of the blob, making larger and have higher chance of getting hit. When hit by an asteroid, the blob splits in half. 

## Player

**Controls blobs. How?** 
- [x] All blobs moves towards the mouse.
**What are blobs?**
- [x] Blobs are circles.
- **What happens when blob gets hit by an asteroid?**
	- One of the is entities destroyed.
		- Blob > Asteroid, then blob gets smaller and splits into halves, asteroid is destroyed.
		- Blob < Asteroid, then asteroid gets smaller, less speed, and blob is destroyed.
- **What happens when blob is destroyed?**
	- Blob drops some value of cells.
	- Cell value depends on size of the blob that was destroyed.
**Blobs can grow. How?**
- Blob eats cell, grows by the cell value.
**If all blobs are destroyed what happens?**
	- Game ends.
## Asteroid

**What are asteroids?**
- Object with shap edges. 
- Varies with size.
- Moves in one direction.
- Original velocity depends on size of the asteroid. 
	- Big → Slow. 
	- Small → Fast.
- **What happens when asteroid hits a blob?**
	- One of the entities is destroyed.
		- Blob > Asteroid, then blob gets smaller and splits into halves, asteroid is destroyed.
		- Blob < Asteroid, then asteroid gets smaller, less speed, and blob is destroyed.
**How they appear on the map?**
- ???

## Cell

**What are cells?**
- [x] Small circles.
- [x] Remain in one position.
- [x] Vary in size. Size depends on the value of cell.
- **What is cell value?**
	-[x] The value that increases the blob size, by some number.
- **What happens when blob eats cell?**
	-[x] Blob eats cell, grows by the cell value.
	-[x] Cell is destroyed.

# Camera
-[x]  Follows the player center position of all blobs.
-[x] Zooms out depending on size of blobs.

[![](https://mermaid.ink/img/pako:eNq1Vm1vmzAQ_ivIn5I2SQNpCcmmSWv3okqdVmnTPkx8uYCTeDMY2SZr2qa_fcbmHdJO2sYHsO853z13Pp95QAELMVqigIIQ7whsOER-bKknJBwHkrDY-nppJB8hwp8ghg3m1sl4rOefVz-UVj9-BfEOhB8b9Ha7FyQQZoH1-lEpvBUSc0bCowpXmNKj4CVlq6PgLYU95qVvPbN8ZPtIU1OjyeRETYwRo6Vz0IjiwQDZc0OEfKoifrKY_opKw4RbLA307FUFn4Ycfg2MeGh9K8PWWJqEIHEPemiSa_qo0Wv5uSExHqwpA2nd2SPLjPbl6M4pZc5w2SaTGbgiPKCliVK7GPDGqhbJ6zhJZY1jS6lKYr7JjFISVptVbUOuVQvz9JsSMG4xGt4yQbLy7IJJF7lkjGKILVAVvcN-DSJ52bzVyGBo5aqVRgT8p9H5wPg7TLHM1Ez0NTsxkYPhSSuXes870ny3G_JD_zkZjx_fdA5aU6Ve1VlJ9-ezuaYnpTtMWUDkvotAEKioObRyWvixgtJhTzHkNuoO86LqSOquIQwHaeeYRCmVPWKRrprSg_mYd8nTVJsu7EL2ZQsJbiS10cCey-SxHlEseiqXi2ZchXhQDI51g-f6QBHTQ0-RE2FgWFFcweMC1se7FZleb0r4BTIVwnaYU0hEVseNQ9Pm2s14gzaJVT-BkKSi3Sw7XEqXZfwwfNZ3ccN0HApyX8vN6XWk9tISCScSv5iRfm5_sGnqNusQ2QFN_5_L7Hp7OdktHv-Oja47kVBtpLqvWyTz27lOU5-kbMGTtVLvJll1u3S9_23e0AhFmEdAQvU3pKn4SG5xhH20VMMQr0E1Hx_5caYKqWRf9nGAlmugAo-QMZn_QJXSBGK0fEB3aDmeTaZT13EXC29hOwtv7s5GaI-Wtu1NHO_cvlAy99x1He8wQveMKRv2ZDpzF3PPPnfcqbe4uJiPEA6J6qaf8n-27FO4fq-R0rNqqOp4ZM7lPsmUNyqjSjlg8ZpsMnnKqRJvpUzE8uwsgycbIrfpahKw6EyQcAtcbncL90zR9sCZYXc-g4vZLAxW9sJbO-f2OpxPbQfQ4aBD_a5pS57iw2-jhhvL?type=png)](https://mermaid.live/edit#pako:eNq1Vm1vmzAQ_ivIn5I2SQNpCcmmSWv3okqdVmnTPkx8uYCTeDMY2SZr2qa_fcbmHdJO2sYHsO853z13Pp95QAELMVqigIIQ7whsOER-bKknJBwHkrDY-nppJB8hwp8ghg3m1sl4rOefVz-UVj9-BfEOhB8b9Ha7FyQQZoH1-lEpvBUSc0bCowpXmNKj4CVlq6PgLYU95qVvPbN8ZPtIU1OjyeRETYwRo6Vz0IjiwQDZc0OEfKoifrKY_opKw4RbLA307FUFn4Ycfg2MeGh9K8PWWJqEIHEPemiSa_qo0Wv5uSExHqwpA2nd2SPLjPbl6M4pZc5w2SaTGbgiPKCliVK7GPDGqhbJ6zhJZY1jS6lKYr7JjFISVptVbUOuVQvz9JsSMG4xGt4yQbLy7IJJF7lkjGKILVAVvcN-DSJ52bzVyGBo5aqVRgT8p9H5wPg7TLHM1Ez0NTsxkYPhSSuXes870ny3G_JD_zkZjx_fdA5aU6Ve1VlJ9-ezuaYnpTtMWUDkvotAEKioObRyWvixgtJhTzHkNuoO86LqSOquIQwHaeeYRCmVPWKRrprSg_mYd8nTVJsu7EL2ZQsJbiS10cCey-SxHlEseiqXi2ZchXhQDI51g-f6QBHTQ0-RE2FgWFFcweMC1se7FZleb0r4BTIVwnaYU0hEVseNQ9Pm2s14gzaJVT-BkKSi3Sw7XEqXZfwwfNZ3ccN0HApyX8vN6XWk9tISCScSv5iRfm5_sGnqNusQ2QFN_5_L7Hp7OdktHv-Oja47kVBtpLqvWyTz27lOU5-kbMGTtVLvJll1u3S9_23e0AhFmEdAQvU3pKn4SG5xhH20VMMQr0E1Hx_5caYKqWRf9nGAlmugAo-QMZn_QJXSBGK0fEB3aDmeTaZT13EXC29hOwtv7s5GaI-Wtu1NHO_cvlAy99x1He8wQveMKRv2ZDpzF3PPPnfcqbe4uJiPEA6J6qaf8n-27FO4fq-R0rNqqOp4ZM7lPsmUNyqjSjlg8ZpsMnnKqRJvpUzE8uwsgycbIrfpahKw6EyQcAtcbncL90zR9sCZYXc-g4vZLAxW9sJbO-f2OpxPbQfQ4aBD_a5pS57iw2-jhhvL)