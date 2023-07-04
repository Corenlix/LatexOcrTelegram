from PIL import Image
from pix2tex.cli import LatexOCR
from fastapi import FastAPI, UploadFile

model = LatexOCR()
app = FastAPI()


@app.post("/latex/")
async def latex_code(file: UploadFile):
    image = Image.open(file.file)
    return {"latex": model(image)}
