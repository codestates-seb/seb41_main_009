import { EMAILREGEXP, PASSWORDREGEXP } from '../constants/Regexp';

const isValidEmail = email => {
  return EMAILREGEXP.test(email);
};

const isValidPassword = password => {
  return PASSWORDREGEXP.test(password);
};

export { isValidEmail, isValidPassword };
