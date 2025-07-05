[CI] GitHub Actions for PR Checks
**Labels:** `ci`, `phase-1`, `low-priority`
**Depends on:** #11

#### 🎯 Goal
Set up automated PR checks with GitHub Actions.

#### 📋 Tasks
- [ ] Create PR check workflow
- [ ] Add test execution
- [ ] Add code quality checks
- [ ] Configure Codecov integration
- [ ] Set up branch protection

#### 📁 Files to Create
- `.github/workflows/pr-check.yml`
- `codecov.yml`

#### 📚 References
- [MVP Spec - GitHub Actions](/docs/mvp-spec-2025-07-05.md#-github-actions-cicd)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)

#### ✅ Acceptance Criteria
- PR checks run automatically
- Tests must pass for merge
- Code quality checks enforced
- Coverage reported to Codecov