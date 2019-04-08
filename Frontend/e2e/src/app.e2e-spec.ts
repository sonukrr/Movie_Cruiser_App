import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';
import { async } from 'q';

describe('movie-cruiser-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Moviecruiserfrontend');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('sonu');
    browser.element(by.id('lastName')).sendKeys('kumar');
    browser.element(by.id('userId')).sendKeys('sonukrr');
    browser.element(by.id('password')).sendKeys('Pass@123');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });
  

  it('should be able to login user and navigate to popular movies', () => {
    browser.element(by.id('userId')).sendKeys('sonukrr');
    browser.element(by.id('password')).sendKeys('Pass@123');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/popular');
  });

  it('should be able to search movies', () => {
    browser.element(by.css('.search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/search');
    browser.element(by.id('search-button-input')).sendKeys('Super');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.css('.movieTitle'));
    expect(searchItems.count()).toBe(20);
    for(let i = 0; i < 1; i += 1) {
      expect(searchItems.get(i).getText()).toContain('Super');
    }
  });

  it('should be able to add movie to watchlist', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.movie-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click();
    browser.driver.sleep(10000);
  });
 
});
