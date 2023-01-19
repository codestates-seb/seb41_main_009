import styled from 'styled-components';
import { Viewer } from '@toast-ui/react-editor';

import { ParagraphMedium, HeadingLarge, HeadingSmall, HeadingMedium } from '../../../styles/typo';
import { PostDummy } from '../../../constants/dummyData';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  width: calc(100% / 12 * 10);
  height: fit-content;
  gap: 10px;
  margin-bottom: 10px;

  .toastui-editor-contents h1 {
    // 본문 H1 태그 스타일 지정
    padding-left: 5px;
    margin-top: 30px;
    ${HeadingLarge}
    color: var(--gray-900);

    &:hover {
      color: var(--gray-700);
    }
  }
  .toastui-editor-contents h2 {
    // 본문 H2 태그 스타일 지정
    padding-left: 5px;
    margin-top: 20px;
    ${HeadingMedium}
    color: var(--gray-800);

    &:hover {
      color: var(--gray-700);
    }
  }

  .toastui-editor-contents h3 {
    // 본문 H3s 태그 스타일 지정
    padding-left: 5px;
    margin-top: 15px;

    color: var(--gray-800);

    ${HeadingSmall}

    &:hover {
      color: var(--gray-700);
    }
  }

  .toastui-editor-contents p {
    // 본문 P 태그 스타일 지정
    width: 100%;
    margin: 0px;
    color: var(--gray-600);

    ${ParagraphMedium}
  }
`;

const PostContentViewer = () => {
  return (
    <Container>
      <Viewer initialValue={PostDummy.data.content} />
    </Container>
  );
};

export default PostContentViewer;
