# Week 6 — Angular / React, GIT, CI/CD

Digital Nurture 5.0 schedule: continue frontend work and practise Git + CI/CD.

## Angular project (continued from Week 5)

Portal location:

`Week-5_Angular/Angular_HandsOn/Navnit/student-course-portal`

Week 5 covers components, forms, services, routing.  
Week 6 focuses on HTTP, NgRx, unit tests (already in the same project) plus Git/CI.

## Git basics used in this repo

- Feature work committed in small messages
- `main` branch for submissions
- `.gitignore` excludes `node_modules`, `dist`, `coverage`, `*.class`

Useful commands:

```bash
git status
git add .
git commit -m "short message"
git push -u origin main
git log --oneline
```

## CI/CD sample

See `.github/workflows/angular-ci.yml` in this folder.

It shows a simple pipeline idea:

1. Checkout code  
2. Setup Node.js  
3. `npm ci`  
4. `npm run build`  
5. `npm test` (headless)

To use it at repo root, copy the workflow to `/.github/workflows/angular-ci.yml` and adjust the `working-directory` path.
