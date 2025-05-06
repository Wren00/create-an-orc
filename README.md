README for Create An Orc project

This project is designed to provide a creative prompt for writers/artists/ttrpg players or anyone looking for a quick
prompt to form an idea for a fantasy character. 
In the initial case this will be creating an orc but the ideas provided can be translated to other fantasy characters.

The website is at MVP phase a single page webapp that provides a randomised pixel art visual of the orc, then a randomised
background for the orc. What is returned should change on page refresh with rate limiting considered for the AI section.
Some pixel art has already been created by the developer. 
The generation of a short description of the character will be done through prompts to Open AI.


MOSCOW targets

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
- User accounts where they can save instances of characters that they wish to keep.
- Rate limiting to prevent an overload of AI requests.


**Could have**
- Eventually expand to other kinds of fantasy characters, like elves, dwarves etc.

**Won't have**
- A full gameified character sheet as this would be too complex.# create-an-orc
