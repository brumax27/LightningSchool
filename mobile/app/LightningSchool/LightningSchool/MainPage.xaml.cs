using System;
using Xamarin.Forms;

namespace LightningSchool
{
	public partial class MainPage : ContentPage
	{
		public MainPage ()
		{
			InitializeComponent ();
		}

		async void OnLogoutButtonClicked (object sender, EventArgs e)
		{
			App.IsUserLoggedIn = false;
			Navigation.InsertPageBefore (new LoginPage (), this);
			await Navigation.PopAsync ();
		}
	}
}
