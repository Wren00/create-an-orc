README for Create An Orc project

**Intro**
This website will provide a randomised name, stats, an AI description built from a randomised prompt and a visual
picture of your character made from randomised pieces of pixel character art. This fantasy character will be an "orc" 
which will be communicated in the branding of the site.


**Problem Definition**
The MVP is a single page webapp that provides a randomised pixel art visual of the orc, then a randomised
background for the orc. What is returned should change on page refresh with rate limiting considered for the AI section.
Some pixel art has already been created by the developer. 
The generation of a short description of the character will be done through prompts to Open AI.
This is to quickly give a user seeking a quick idea for art or writing a compelling starting off point.

**Who is it for?**
This project is designed to provide a creative prompt for writers/artists/ttrpg players or anyone seeking a
prompt to form an idea for a fantasy character.


MOSCOW analysis

**Must have**

- A pixel art image of the randomised character. This has been created through:
1. A shadow base
2. A face with skin colour
3. Upper torso clothes
4. Lower torso clothes
These individual images are layered together on the screen and will appear in the same place on page refresh.
- A randomised name which has been built out of syllables provided in a JSON file.
- A randomised description created via prompts to Open AI. The prompts should have randomised values each time it is sent
through to the AI. The AI generates the description which will be sent back and displayed.
- Some randomised stats

**Should have**
- User accounts where a user can save instances of characters that they wish to keep record of.
- Rate limiting to prevent an overload of AI requests.
- The website should be viewable and usable on different screen sizes.


**Could have**
- Eventually expand to other kinds of fantasy characters, like elves, dwarves etc. Each new type of potential character
will need to be added as separate updates.
- Users can share their favourite creations with other users.

**Won't have**
- A full gameified character sheet for ttrpg players as this would be too complex.
- A massively expanded description of the character as this is just meant to be a simple prompt to inspire user
creativity.
