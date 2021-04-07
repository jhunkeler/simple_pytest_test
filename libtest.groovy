// Obtain files from source control system.
if (utils.scm_checkout()) return


// Allow modification of the job configuration, affects all relevant build configs.
// Pass this object in the argument list to the`run()` function below to apply
// these settings to the job's execution.
jobconfig = new JobConfig()
jobconfig.post_test_summary = true

// Define each build configuration, copying and overriding values as necessary.
bc0 = new BuildConfig()
bc0.nodetype = "python37"
bc0.name = "libtest"
bc0.build_cmds = ["true"]
bc0.test_cmds = ["conda install -q -y pytest requests astropy",
                 "pytest tests --basetemp=tests_output --junitxml results.xml --remote-data tests/"]
bc0.failedUnstableThresh = 1
bc0.failedFailureThresh = 6


// Iterate over configurations that define the (distibuted) build matrix.
// Spawn a host of the given nodetype for each combination and run in parallel.
// Also apply the job configuration defined in `jobconfig` above.
utils.run([bc0, jobconfig])
