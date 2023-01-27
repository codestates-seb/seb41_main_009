import axios from 'axios';
import { EMAIL_VALIDATION_FAILURE, EMAIL_VALIDATION_SUCCESS } from '../../../constants/Messages';
import { Box, Label, SignupInput, CheckButton } from '../../atoms/signup/SignupComponents';

const EmailValidationBox = ({
  email,
  emailValidation,
  emailValidationCode,
  setEmailValidationCode,
  emailValidationMessage,
  setEmailValidationMessage,
}) => {
  const onEmailValidationInput = e => {
    const emailValdationValue = e.target.value;

    setEmailValidationCode(emailValdationValue);
  };

  const verifyEmailValidation = () => {
    const url = 'auth/certifications';
    const body = {
      email,
      emailValidationCode,
    };

    axios
      .patch(url, body)
      .then(res => {
        console.log(res);
        setEmailValidationMessage(EMAIL_VALIDATION_SUCCESS);
      })
      .catch(err => {
        console.log(err);
        setEmailValidationMessage(EMAIL_VALIDATION_FAILURE);
      });
  };

  return (
    <div>
      {emailValidation ? (
        <Box>
          <Label>Email Validation</Label>
          <SignupInput
            placeholder="Enter Your Validation Code"
            onChange={onEmailValidationInput}
            message={emailValidationMessage}
            asideInput={<CheckButton onClick={verifyEmailValidation}>인증번호 확인</CheckButton>}
          />
        </Box>
      ) : null}
    </div>
  );
};

export default EmailValidationBox;
