import styled from 'styled-components';
import { ParagraphMedium } from '../../styles/typo';

const TextArea = ({ maxLength, placeholder, handleContent }) => {
  return <Container maxLength={maxLength} placeholder={placeholder} onChange={handleContent} />;
};

const Container = styled.textarea`
  ${ParagraphMedium};
  padding: 0px 0px 100px 0px;
  height: 310px;
  border: none;
  outline-color: white;
  overflow-x: hidden;
`;

export default TextArea;
