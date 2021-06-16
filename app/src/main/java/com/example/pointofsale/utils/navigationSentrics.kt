package com.example.pointofsale.utils
/*
class NavigationImpl(
private val analyticRepairs: AnalyticsRepairs
) : Navigation {
    override fun navigateBack(fromActivity: AppCompatActivity, defaultBehavior: () -> Unit) {
        try {
            Timber.d("NavigationImpl_TAG: navigateBack: from: ${fromActivity::class.simpleName}")

            //region validate No Return Activity
            val noReturn = noReturnActivity.find { fromActivity::class.java == it }
            if (noReturn != null) return
            //endregion

            //region get Previous Activity or default behavior
            val toActivity = getPreviousScreen(fromActivity)
            if (toActivity == null) {
                defaultBehavior()
                return
            }
            //endregion

            navigate(fromActivity, toActivity)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    override fun navigateHome(fromActivity: AppCompatActivity, extras: Bundle?) {
        try {
            Timber.d("NavigationImpl_TAG: navigateHome: from: ${fromActivity::class.simpleName}, extras: $extras")
            if (Session.currentActivity != null && Session.currentActivity!!::class.java == MainActivity::class.java) return
            navigate(fromActivity, MainActivity::class.java)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    override fun navigateSplash(fromActivity: AppCompatActivity, extras: Bundle?) {
        try {
            Timber.d("NavigationImpl_TAG: navigateSplash: ")
            navigate(fromActivity, SplashActivity::class.java)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    override fun onDone(fromActivity: AppCompatActivity, extras: Bundle?) {
        try {
            Timber.d("NavigationImpl_TAG: onDone: from: ${fromActivity::class.simpleName}, extras: $extras")
            val toActivity = getNextScreen(fromActivity) ?: return

            navigate(fromActivity, toActivity, extras)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    override fun navigateToApp(context: Context, app: App, extras: Bundle?) {
        try {
            val innerApp =
                Navigation.DeepLink.getDeepLink(app.deepLink)

            //region Check / Open third party app if applies
            if (app.deepLink.isNotEmpty() && innerApp == null) {
                val intent =
                    context.packageManager.getLaunchIntentForPackage(app.deepLink) ?: return
                context.startActivity(intent)
                return
            }
            //endregion

            if (innerApp == Navigation.DeepLink.ANDROID_SETTINGS) {
                context.startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
                return
            }

            val appView = when (innerApp) {
                Navigation.DeepLink.REPAIRS -> {
                    analyticRepairs.onSeeAll()
                    WorkOrderActivity::class.java
                }
                Navigation.DeepLink.NEW_REPAIR -> {
                    analyticRepairs.onNewRepair()
                    RoomActivity::class.java
                }
                Navigation.DeepLink.CALENDAR -> CalendarActivity::class.java
                Navigation.DeepLink.ACTIVITIES -> ActivitiesActivity::class.java
                Navigation.DeepLink.DINING -> DiningActivity::class.java
                Navigation.DeepLink.MUSIC -> {
                    Session.isFaith = false
                    MusicActivity::class.java
                }
                Navigation.DeepLink.WIFI -> WiFiActivity::class.java
                Navigation.DeepLink.UNSUBSCRIBE -> UnRegistrationActivity::class.java
                Navigation.DeepLink.REBOOT -> RebootActivity::class.java
                Navigation.DeepLink.ADMIN_SETTINGS -> AdminActivity::class.java
                Navigation.DeepLink.APP_VERSION -> AppVersionActivity::class.java
                Navigation.DeepLink.WEATHER -> WeatherActivity::class.java
                Navigation.DeepLink.GOOGLE_HOME -> AdminHomeActivity::class.java
                Navigation.DeepLink.PAD_TV -> com.Pad.tvapp.MainActivity::class.java
                Navigation.DeepLink.BUTTON_TESTER -> ButtonTesterActivity::class.java
                Navigation.DeepLink.ETHERNET_SWITCH -> NetworkTypeActivity::class.java
                Navigation.DeepLink.TV -> Engage360TVActivity::class.java//EngageTVActivity::class.java
                Navigation.DeepLink.SETTINGS -> SettingsActivity::class.java
                Navigation.DeepLink.HOME_VIEW -> HomeViewActivity::class.java
                Navigation.DeepLink.NETWORK_INFO -> NetworkInfoActivity::class.java
                Navigation.DeepLink.LOG_CONFIG -> LogActivity::class.java
                Navigation.DeepLink.LOG_VIEWER -> LogViewerActivity::class.java
                Navigation.DeepLink.LOG_MANAGER -> LogManagerActivity::class.java
                Navigation.DeepLink.EXERCISES -> if (Session.eulaAccepted) ExerciseActivity::class.java else LicenseAgreementActivity::class.java //HERE THE IF CONDITION
                Navigation.DeepLink.TV_PREFERENCES -> TVPreferencesActivity::class.java
                Navigation.DeepLink.CRASH_TEST -> CrashingActivity::class.java
                Navigation.DeepLink.TUNING_FILE_READER -> TuningFileReaderActivity::class.java
                Navigation.DeepLink.HDMI_IN -> {
                    Session.currentActivity = null
                    CameraActivity::class.java
                }
                Navigation.DeepLink.UPDATE_MANAGER -> UpdateManagerActivity::class.java
                Navigation.DeepLink.TERMINAL_TEST -> TerminalActivity::class.java
                Navigation.DeepLink.AWS_TRANSCRIBE -> AWSTranscribeTestActivity::class.java
                Navigation.DeepLink.DVR -> DVRLandingActivity::class.java
                Navigation.DeepLink.VOICE -> VoiceActivity::class.java
                Navigation.DeepLink.FAITH -> {
                    Session.isFaith = true
                    MusicActivity::class.java
                }
                else -> null
            } ?: return

            navigate(context, appView, extras)

        } catch (e: Exception) {
            Timber.d("NavigationImpl_TAG: navigateToApp: error: ${e.message}")
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    override fun navigateToDetails(fromActivity: AppCompatActivity, extras: Bundle?) {
        try {
            Timber.d("NavigationImpl_TAG: navigateToDetails: from: ${fromActivity::class.java}, extras: $extras")
            val toActivity = getDetailsScreen(fromActivity) ?: return

            navigate(fromActivity, toActivity, extras)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    private fun <T> navigate(
        context: Context,
        view: Class<T>,
        extras: Bundle? = null
    ) {
        try {
            Timber.d("NavigationImpl_TAG: navigate: $context , view: $view")
            val intent = Intent(context, view)
            if (extras != null) {
                intent.putExtras(extras)
            }

            if (view == MainActivity::class.java) {
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

            context.startActivity(intent)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    private fun getPreviousScreen(fromActivity: AppCompatActivity): Class<*>? =
        when (fromActivity::class) {
            WorkOrderActivity::class -> MainActivity::class.java
            UnRegistrationActivity::class -> AdminActivity::class.java
            LicenseAgreementActivity::class -> MainActivity::class.java
            ExerciseActivity::class -> MainActivity::class.java
            DVRLandingActivity::class -> MainActivity::class.java
            AdminActivity::class -> MainActivity::class.java
            Engage360TVActivity::class -> MainActivity::class.java
            CalendarActivity::class -> MainActivity::class.java
            MusicActivity::class -> MainActivity::class.java
            ActivitiesActivity::class -> MainActivity::class.java
            DiningActivity::class -> MainActivity::class.java
            IssueActivity::class -> MainActivity::class.java
            WorkOrderActivity::class -> MainActivity::class.java
            SettingsActivity::class -> MainActivity::class.java
            WeatherActivity::class -> MainActivity::class.java
            else -> null
        }

    private fun getNextScreen(fromActivity: AppCompatActivity): Class<*>? =
        when (fromActivity::class) {
            SplashActivity::class -> if (Session.token.isNotEmpty() && Session.communityId.isNotEmpty()) MainActivity::class.java else RegistrationActivity::class.java
            RegistrationActivity::class -> MainActivity::class.java
            WorkOrderActivity::class -> RoomActivity::class.java
            RoomActivity::class -> RepairItemActivity::class.java
            RepairItemActivity::class -> IssueActivity::class.java
            IssueActivity::class -> RepairRequestActivity::class.java
            RepairRequestActivity::class -> WorkOrderActivity::class.java
            CalendarActivity::class -> ActivitiesActivity::class.java
            ActivitiesActivity::class -> ActivityDetailsActivity::class.java
            WiFiActivity::class -> WiFiDetailsActivity::class.java
            UnRegistrationActivity::class -> SplashActivity::class.java
            NetworkTypeActivity::class -> SplashActivity::class.java
            DVRLandingActivity::class -> DVRRecordingDetailsActivity::class.java
            DVRRecordingDetailsActivity::class -> DVRPlayerActivity::class.java
            DVRSeeAllActivity::class -> DVRRecordingDetailsActivity::class.java
            LicenseAgreementActivity::class -> ExerciseActivity::class.java
            else -> null
        }

    private fun getDetailsScreen(fromActivity: AppCompatActivity): Class<*>? =
        when (fromActivity::class) {
            CalendarActivity::class -> CalendarActivityDetailsActivity::class.java
            WorkOrderActivity::class -> RepairRequestActivity::class.java
            ExerciseActivity::class -> ExerciseDetailsActivity::class.java
            ExerciseDetailsActivity::class -> ExercisePlayerActivity::class.java
            DiningActivity::class -> MealDetailsActivity::class.java
            DVRLandingActivity::class -> RecordingOptionsActivity::class.java
            DVRScheduleListActivity::class -> RecordingScheduleDetailsActivity::class.java
            else -> null
        }

    override fun navigateToNoInternet(fromActivity: AppCompatActivity) {
        try {
            Timber.d("NavigationImpl_TAG: navigateToNetworkType: ")
            val toActivity = when (fromActivity::class) {
                SplashActivity::class -> NetworkTypeActivity::class.java
                else -> NetworkTypeActivity::class.java
            }
            navigate(fromActivity, toActivity, null)
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    private val noReturnActivity = listOf<Class<*>>(
        SplashActivity::class.java,
        RegistrationActivity::class.java,
        MainActivity::class.java
    )

    */